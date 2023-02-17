package net.skhu.codingFriends.DesignPattern.Strategy.mailMessage;

import lombok.Getter;
import net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.subject.*;
import net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.text.*;
import net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.to.SendToOneUser;
import net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.to.SendToStrategy;

import java.util.Arrays;

@Getter
public enum MailType {
    DEFALUT("", new SendToOneUser(), new Applied(), new AppliedText()),
    ACCEPT("accepted", new SendToOneUser(), new Accepted(), new AcceptedText()),
    REJECT("rejected", new SendToOneUser(), new Rejected(), new RejectedText()),
    NOTICE("notice", new SendToOneUser(), new Notice(), new NoticeText());

    private final String type;
    private SendToStrategy sendToStrategy;
    private SubjectStrategy subjectStrategy;
    private TextStrategy textStrategy;

    MailType(String type, SendToStrategy sendToStrategy, SubjectStrategy subjectStrategy, TextStrategy textStrategy) {
        this.type = type;
        this.sendToStrategy = sendToStrategy;
        this.subjectStrategy = subjectStrategy;
        this.textStrategy = textStrategy;
    }

    public static MailType find(String type) {
        return Arrays.stream(values())
                .filter(accountStatus -> accountStatus.type.equals(type))
                .findAny()
                .orElse(DEFALUT);
    }
}