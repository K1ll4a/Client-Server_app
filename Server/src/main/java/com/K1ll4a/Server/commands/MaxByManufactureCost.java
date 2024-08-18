package com.K1ll4a.Server.commands;

import com.K1ll4a.Server.rulers.CollectionRuler;
import com.K1ll4a.global.facility.Response;
import com.K1ll4a.global.facility.Mclass;


import java.util.Comparator;
import java.util.Optional;

public class MaxByManufactureCost extends Command {
    private final CollectionRuler collectionRuler;
    public MaxByManufactureCost(CollectionRuler collectionRuler){
        super("MaxByManufactureCost","вывести любой объект из коллекции, значение поля manufactureCost которого является максимальным");
        this.collectionRuler = collectionRuler;
    }

    @Override
    public Response apply(String[] arguments , Mclass mclass,String login , String password){
        if(!arguments[1].isEmpty()){
            return new Response("Неправильное количество аргументов!\n" + "Использование: " + getName() + " '");
        }
        Optional<Mclass> maxManufactureCostMclass = collectionRuler.getCollection().stream()
                .max(Comparator.comparing(Mclass::getManufactureCost));
        if (maxManufactureCostMclass.isPresent()) {
            return new Response("Объект с максимальным значением manufactureCost: " + maxManufactureCostMclass.get());
        } else {
            return new Response("Коллекция пуста");
        }
    }
}
