package com.K1ll4a.Server.commands;


import com.K1ll4a.Server.rulers.CollectionRuler;
import com.K1ll4a.global.facility.Mclass;
import com.K1ll4a.global.facility.Response;

public class Show  extends  Command{
    private final CollectionRuler collectionRuler;
    public Show(CollectionRuler collectionRuler){
        super("show","вывести все элементы коллекции");
        this.collectionRuler = collectionRuler;
    }

    @Override
    public Response apply(String[] arguments, Mclass mclass,String login ,String password){
        if(!arguments[1].isEmpty()){
            return new Response("Неправильное количество аргументов!\n" + "Использование: " + getName() + " '");
        }
        return new Response(collectionRuler.toString());
    }
}
