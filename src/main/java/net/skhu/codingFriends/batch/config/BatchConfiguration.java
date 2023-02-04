package net.skhu.codingFriends.batch.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.codingFriends.batch.mapper.OrderMapper;
import net.skhu.codingFriends.batch.processor.OrderItemProcessor;
import net.skhu.codingFriends.batch.writer.OrderWriter;
import net.skhu.codingFriends.entity.apply;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Random;

@EnableBatchProcessing
@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchConfiguration {

    public final JobBuilderFactory jobBuilderFactory;

    public final StepBuilderFactory stepBuilderFactory;


    final JobLauncher jobLauncher;

    final DataSource dataSource;


    private final String JOB_NAME = "emailSenderJob";
    private final String STEP_NAME = "emailSenderStep";

    Random random = new Random();
    int randomWithNextInt = random.nextInt();


    @Bean(name = "emailSenderJob")
    public Job emailSenderJob() {
        return this.jobBuilderFactory.get(JOB_NAME+randomWithNextInt)
                .start(emailSenderStep())
                .build();
    }

    @Bean
    public Step emailSenderStep() {
        return this.stepBuilderFactory
                .get(STEP_NAME)
                .<apply, apply>chunk(100)
                .reader(activeOrderReader())
                .processor(orderItemProcessor())
                .writer(orderWriter())
                .build();
    }

    @Bean
    public ItemProcessor<apply, apply> orderItemProcessor() {
        return new OrderItemProcessor();
    }

    @Bean
    public ItemWriter<apply> orderWriter() {
        return new OrderWriter();
    }

    @Bean
    public ItemReader<apply> activeOrderReader() {
        String sql = "SELECT * FROM apply WHERE applyStatus=\"신청\" and mail_sent=0";
        return new JdbcCursorItemReaderBuilder<apply>()
                .name("activeApplyReader")
                .sql(sql)
                .dataSource(dataSource)
                .rowMapper(new OrderMapper())
                .build();
    }
}
