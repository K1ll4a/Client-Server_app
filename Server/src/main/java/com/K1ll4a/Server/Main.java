package com.K1ll4a.Server;

import com.K1ll4a.Server.managers.SocketServer;
import com.K1ll4a.Server.rulers.CollectionRuler;
import com.K1ll4a.Server.rulers.CommandRuler;

import com.K1ll4a.global.tools.Console;
import com.K1ll4a.global.tools.MyConsole;
import com.K1ll4a.Server.commands.*;
import com.K1ll4a.Server.rulers.DatabaseRuler;




import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Console console = new MyConsole();
        DatabaseRuler databaseRuler = new DatabaseRuler();
        try {
            databaseRuler.connect();
        } catch (Exception e) {
            System.err.println("Ошибка при подключении к базе данных");
            e.printStackTrace();
            System.exit(1);
        }

        CollectionRuler collectionRuler = new CollectionRuler(databaseRuler);

        if (!collectionRuler.init()){
            System.err.println("Ошибка инициализации CollectionRuler");
            System.exit(1);
        }

        CommandRuler commandRuler = new CommandRuler(){{
            register("info",new Info(collectionRuler));
            register("help",new Help(this));
            register("show",new Show(collectionRuler));
            register("add",new Add(collectionRuler));
            register("update",new UpdateById(collectionRuler,databaseRuler));
            register("remove_by_id",new RemoveById(collectionRuler,databaseRuler));
            register("clear",new Clear(collectionRuler));
            register("save",new Save(collectionRuler));
            register("exit",new Exit());
            register("add_if_min",new AddIfMin(collectionRuler));
            register("RemoveLast",new RemoveLast(collectionRuler,databaseRuler));
            register("RemoveGreater",new RemoveGreater(collectionRuler));
            register("count_greater_than_unit_of_measure",new CountLessManufactureCost(collectionRuler));
            register("MaxByManufactureCost",new MaxByManufactureCost(collectionRuler));
            register("MinByManufactureCost",new MinByManufactureCost(collectionRuler));
            register("login", new Login(databaseRuler));
            register("registration", new Registration(databaseRuler));
        }};

        System.out.println("Сервер запускается...");
        System.out.println("Сервер запущен и слушает на порту 8080");

        new SocketServer("localhost", 5432, commandRuler).start();

        //System.out.println("Сервер запускается...");

        //System.out.println("Сервер запущен и слушает на порту 8080");
    }
}