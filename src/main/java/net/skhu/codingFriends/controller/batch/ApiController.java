package net.skhu.codingFriends.controller.batch;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.codingFriends.service.EmailServiceImpl;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.SendFailedException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    final EmailServiceImpl emailService;

    final private JobLauncher jobLauncher;


    @Autowired
    @Qualifier("emailSenderJob")
    private Job emailSenderJob;

    @GetMapping("/test")
    public ResponseEntity<ResponseMessage> getAllOrders() {
        try {
            emailService.sendSimpleMessage("hello@world.com", "This is the message", "Thank you for registering with us");
        } catch (SendFailedException sendFailedException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("failed to send email"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("email sent"));
    }


    @PostMapping("/send/notification")
    public ResponseEntity<ResponseMessage> sendEmails() {
        Random random = new Random();
        int randomWithNextInt = random.nextInt();

        JobParameter param = new JobParameter(String.valueOf(randomWithNextInt));
        JobParameters jobParameters = new JobParametersBuilder().addParameter("unique", param).toJobParameters();

        try {
            final JobExecution jobExecution = jobLauncher.run(emailSenderJob, jobParameters);
                Date create = jobExecution.getStartTime();
                Date end = jobExecution.getEndTime();
                int diff = end.getSeconds() - create.getSeconds();
                log.debug("difference = {}", diff);
                TimeUnit.SECONDS.sleep(diff);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success"));
        } catch (JobInstanceAlreadyCompleteException | JobExecutionAlreadyRunningException | JobParametersInvalidException |InterruptedException | JobRestartException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(e.getMessage()));
        }
    }
}
