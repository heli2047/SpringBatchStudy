package com.studybath.springBatchFirstProject;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@EnableBatchProcessing
@Component
public class BatchcConfig {
    
    private JobBuilderFactory jobBuilderFactory;
    
    private StepBuilderFactory stepBuilderFactory;

    public BatchcConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }
    
    @Bean
    public Job imprimeOlaJob(){
        return jobBuilderFactory
            .get("imprimeOlaJob")
            .start(imprimeOlaStep())
            .build();
    }

    public Step imprimeOlaStep(){
        return stepBuilderFactory
            .get("imprimeOlaStep")
            .tasklet(new Tasklet(){

                @Override
                public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                    System.out.println("Ola Mundo");
                    return RepeatStatus.FINISHED;
                }
            })
            .build();

    }
}
