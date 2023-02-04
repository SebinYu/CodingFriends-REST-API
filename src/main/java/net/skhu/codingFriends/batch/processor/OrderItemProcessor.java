package net.skhu.codingFriends.batch.processor;

import lombok.extern.slf4j.Slf4j;
import net.skhu.codingFriends.entity.apply;
import net.skhu.codingFriends.repository.UserRepository;
import net.skhu.codingFriends.service.EmailServiceImpl;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.SendFailedException;

@Slf4j
public class OrderItemProcessor implements ItemProcessor<apply, apply> {

    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    UserRepository userRepository;
    @Override
    public apply process(apply apply) throws Exception {
        log.debug("processor: {}", apply);
        try {
            emailService.sendSimpleMessage(userRepository.findById(apply.getUserIdtemp().intValue()).get().getEmail(), "Your Order has been shipped!", "Thank you for shopping with us");
            apply.setMail_sent(true);
        } catch (SendFailedException sendFailedException) {
            log.debug("error: {}", sendFailedException.getMessage());
        }
        return apply;
    }
}
