package proxies;

import model.Comment;
import org.springframework.stereotype.Repository;

@Repository
public class EmailCommentNotificationProxy implements CommentNotificationProxy{

    @Override
    public void sendComment(Comment comment) {
        System.out.println("Send email notification with comment: " + comment.getText());
    }
}
