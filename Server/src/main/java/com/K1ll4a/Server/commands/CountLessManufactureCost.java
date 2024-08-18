package com.K1ll4a.Server.commands;

import com.K1ll4a.global.facility.Mclass;
import com.K1ll4a.global.facility.Response;
import com.K1ll4a.Server.rulers.CollectionRuler;

public class CountLessManufactureCost extends Command {
    private final CollectionRuler collectionRuler;
    public CountLessManufactureCost(CollectionRuler collectionRuler){
        super("CountLessThanManufactureCost","вывести количество элементов, значение поля manufactureCost которых меньше заданного");
        this.collectionRuler = collectionRuler;
    }

    @Override
    public Response apply(String[] arguments , Mclass mclass,String login,String password){
        if(arguments[1].isEmpty()){
            return new Response("Неправильное количество аргументов!\n" + "Использование: " + getName() + " 'manufactureCost'");
        }
        double manufactureCost = Double.parseDouble(arguments[1]);
        long count = collectionRuler.getCollection().stream()
                .filter(m -> m.getManufactureCost() < manufactureCost)
                .count();
        return new Response("Количество элементов с manufactureCost меньше " + manufactureCost + ": " + count);
    }
}
