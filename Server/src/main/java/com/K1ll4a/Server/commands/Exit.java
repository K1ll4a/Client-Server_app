package com.K1ll4a.Server.commands;


import com.K1ll4a.global.facility.Mclass;
import com.K1ll4a.global.facility.Response;

public class Exit extends Command{
    public Exit(){
        super("exit", "завершить программу (без сохранения в файл)");
    }

    @Override
    public Response apply(String[] arguments, Mclass mclass,String login,String password){
        if(!arguments[1].isEmpty()){
            console.println("Неправильное количество аргументов!");
            console.println("использование '"  +getName() + "'");
            return new Response("");
        }
        System.out.println("Завершение программы");
        System.exit(1);
        return new Response("Программа завершена");
    }
}
