package com.K1ll4a.Server.commands;



import com.K1ll4a.Server.rulers.DatabaseRuler;
import com.K1ll4a.Server.rulers.CollectionRuler;
import com.K1ll4a.global.facility.Mclass;
import com.K1ll4a.global.facility.Response;

import javax.xml.crypto.Data;
import java.sql.SQLException;

public class RemoveLast  extends  Command{
    private final CollectionRuler collectionRuler;
    private final DatabaseRuler databaseRuler;

    public RemoveLast(CollectionRuler collectionRuler,DatabaseRuler databaseRuler){
        super("remove_last","удалить последний элемент из коллекции");
        this.collectionRuler = collectionRuler;
        this.databaseRuler = databaseRuler;
    }

    @Override
    public Response apply(String[] arguments, Mclass mclass,String login , String password){
        if (!arguments[1].isEmpty()){
            return new Response("Неправильное количество аргументов!\n" + "Использование: " + getName() + " '");
        }
        if(!collectionRuler.collectionIsEmptu()){
            try{
                var deletable = collectionRuler.getFirtsMclassToRemove();
                var userID = collectionRuler.getUserid(login);
                var deletableId = deletable.getId();
                var checkingUserID = collectionRuler.isCorrectID(deletableId);
                if(userID==checkingUserID){
                    collectionRuler.removeLast();
                    return new Response("Mclass удалён");
                }else {
                    return new Response("Mclass не удален! Возможные причины: вы попытались удалить чужой mclass");
                }
            }catch (SQLException e){
                return new Response("Ошибка удаления mclass  в базе данных");
            }
        }else {
            return new Response("Невозможно удалить элемент , так как коллекция пуста");
        }
    }
}