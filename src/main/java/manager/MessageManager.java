package manager;

import db.DBConnectionProvider;
import model.Message;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MessageManager {
    private Connection connection;

    public MessageManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void add(Message message) throws SQLException {
        String query="insert into message (message,from_id,to_id)" +
                "values(?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,message.getMessage());
        preparedStatement.setLong(2,message.getFromId());
        preparedStatement.setLong(3,message.getToId());
        preparedStatement.executeUpdate();
        ResultSet resultSet=preparedStatement.getGeneratedKeys();
        if (resultSet.next()){
            message.setId(resultSet.getInt(1));
        }
    }


    public List<Message> getMessagesByFromAndToId(int fromId, int toId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM message WHERE (from_id = ? and to_id = ?) OR " +
                        "(from_id = ? and to_id = ?)");
        preparedStatement.setLong(1, fromId);
        preparedStatement.setLong(2, toId);
        preparedStatement.setLong(3, toId);
        preparedStatement.setLong(4, fromId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Message> messages = new LinkedList<Message>();
        while (resultSet.next()) {
            Message message = new Message();
            message.setId(resultSet.getInt("id"));
            message.setFromId(resultSet.getInt("from_id"));
            message.setToId(resultSet.getInt("to_id"));
            messages.add(message);
        }
        return messages;
    }

    public List<Message> getNewMessagesToUserId(long userId) throws SQLException {
        String query="select * from message where (to_id=?) and status='NEW'";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Message> messages = new LinkedList<Message>();
        while (resultSet.next()) {
            Message message = new Message();
            message.setId(resultSet.getInt(1));
            message.setMessage(resultSet.getString(2));
            message.setFromId(resultSet.getInt(3));
            message.setToId(resultSet.getInt(4));
            messages.add(message);
        }
        return messages;

    }

}
