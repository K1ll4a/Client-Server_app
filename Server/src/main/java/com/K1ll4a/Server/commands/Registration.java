package com.K1ll4a.Server.commands;

import com.K1ll4a.global.facility.Response;
import com.K1ll4a.global.facility.Mclass;
import com.K1ll4a.Server.rulers.DatabaseRuler;



import java.sql.SQLException;

import static com.K1ll4a.Server.tools.PasswordHashing.*;

public class Registration extends Command {
    private  DatabaseRuler databaseRuler;
    public Registration(DatabaseRuler databaseRuler){
        super("registration" , "Добавить пользователя");
        this.databaseRuler = databaseRuler;
    }

    @Override
    public Response apply(String[] arguments , Mclass mclass , String login ,String password) {
        try {
            String salt = generateSalt();
            boolean isUserAdded = databaseRuler.insertUser(login,hashPasswordWithSalt(password,salt),salt);
            if (isUserAdded) {
                return new Response("Вы успешно авторизовались" , true);
            } else {
                return new Response("Ошибка! Не удалось добавить пользователя");
            }
        } catch (Exception e){
            return new Response("Ошибка! Не удалось добавить пользователя");
        }
    }
}
