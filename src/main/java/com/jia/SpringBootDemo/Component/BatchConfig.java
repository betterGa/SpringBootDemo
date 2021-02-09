package com.jia.SpringBootDemo.Component;
import com.jia.SpringBootDemo.entry.personEntry;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Configuration

// 开启批量处理器
@EnableBatchProcessing

@Import(DruidDBConfig.class)
public class BatchConfig {

    // 读取数据
    @Bean
    public ItemReader<personEntry> reader() {

        // new 一个文件阅读器
        FlatFileItemReader<personEntry> reader = new FlatFileItemReader<>();

        // 设置文件
        reader.setResource(new ClassPathResource("person.csv"));

        // 文件与实体类映射
        // 设置 key
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[]{"id", "name", "age", "sex"});

        // 设置实体对应
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper<personEntry>();
        fieldSetMapper.setTargetType(personEntry.class);

        DefaultLineMapper<personEntry> personDefaultLineMapper = new DefaultLineMapper<personEntry>();
        personDefaultLineMapper.setLineTokenizer(lineTokenizer);
        personDefaultLineMapper.setFieldSetMapper(fieldSetMapper);

        // 关联到阅读器
        reader.setLineMapper(personDefaultLineMapper);

        return reader;
    }

    // 处理数据
    @Bean
    public ItemProcessor<personEntry, personEntry> processor() {
        csvItemProcesser csvItemProcesser = new csvItemProcesser();
        csvItemProcesser.setValidator(beanValidator());
        return csvItemProcesser;
    }

    @Bean
    public BeanValidator beanValidator() {
        return new BeanValidator();
    }


    // 写数据
    @Bean
    public ItemWriter<personEntry> writer(DataSource dataSource) {
        JdbcBatchItemWriter writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<personEntry>());
        String sql = "insert into person values(:id,:name,:age,:sex)";
        writer.setSql(sql);
        writer.setDataSource(dataSource);
        return writer;
    }


    // 注册 Job 容器
    @Bean
    public JobRepository cvsJobRepository(DataSource dataSource, PlatformTransactionManager manager) throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDatabaseType("mysql");
        jobRepositoryFactoryBean.setTransactionManager(manager);
        jobRepositoryFactoryBean.setDataSource(dataSource);
        return jobRepositoryFactoryBean.getObject();
    }

    // 容器启动器
    @Bean
    public SimpleJobLauncher csvJobLauncher(DataSource dataSource, PlatformTransactionManager manager) throws Exception {
        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(cvsJobRepository(dataSource, manager));
        return simpleJobLauncher;
    }

    // 实际 job
    @Bean
    public Job importJob(JobBuilderFactory jobs, Step step) {
        return jobs.get("importCsvJob")
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .end()

                // 关联监听器
                .listener(jobListener())

                .build();
    }


    // 监听器
    @Bean
    public JobListener jobListener() {
        return new JobListener();
    }


    @Bean
    public Step step(StepBuilderFactory stepBuilderFactory, ItemReader<personEntry> reader,
                     ItemWriter<personEntry> writer, ItemProcessor<personEntry, personEntry> processor) {

        return stepBuilderFactory.get("step")

                // 数据是按块处理的，chunk 的机制是
                // 每次拿一条数据，积累到 65000 再一次性提交给数据库
                .<personEntry,personEntry>chunk(65000)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
