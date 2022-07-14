package dev.com.thejungle.app.app;

import org.eclipse.jetty.server.ServerConnector;

import dev.com.thejungle.app.appcontroller.appcontroller.AppController;
import dev.com.thejungle.app.appcontroller.controllers.ChatController;
import dev.com.thejungle.app.appcontroller.controllers.UserController;
import dev.com.thejungle.dao.implementations.ChatDAO;
import dev.com.thejungle.dao.implementations.UserDAO;
import dev.com.thejungle.service.implementations.ChatService;
import dev.com.thejungle.service.implementations.UserService;
import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {

        int port = Integer.parseInt(System.getProperty("PORT", "8080"));
        org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setHost(System.getProperty("HOST", "0.0.0.0"));
        connector.setPort(port);
        server.setConnectors(new ServerConnector[] { connector });


        Javalin app = Javalin.create(config -> {
            config.server(() -> server);
            config.enableCorsForAllOrigins();
            config.enableDevLogging();
        });

        // Chat Controller
        ChatDAO chatDAO = new ChatDAO();
        ChatService chatService = new ChatService(chatDAO);
        ChatController chatController = new ChatController(chatService);

        // User Controller
        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        UserController userController = new UserController(userService);

        // App Controller
        AppController appController = new AppController(app, chatController, userController);

        appController.createChatRoutes();
        appController.createUserRoutes();

        app.start(port);
    }

}

