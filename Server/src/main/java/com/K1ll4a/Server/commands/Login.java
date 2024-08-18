package com.K1ll4a.Server.commands;

import com.K1ll4a.global.facility.Mclass;
import com.K1ll4a.global.facility.Response;
import com.K1ll4a.Server.rulers.DatabaseRuler;



import java.sql.SQLException;

public class Login extends Command {
    private DatabaseRuler databaseRuler;

    public Login(DatabaseRuler databaseRuler){
        super("login" , "Авторизоваться");
        this.databaseRuler = databaseRuler;
    }

    @Override
    public Response apply(String[] arguments,Mclass mclass,String login ,String password){
        try{
            if(databaseRuler.checkUser(login,password)){
                return new Response("Вы успешно вошли в систему", true);
            }else {
                return new Response("Непредвиденная ошибка во время авторизации пользователя");
            }
        }catch (SQLException e){
            return new Response("Не удалось авторизовать пользователя.Возможно вы укащали неверный логин или пароль");
        }
    }
}