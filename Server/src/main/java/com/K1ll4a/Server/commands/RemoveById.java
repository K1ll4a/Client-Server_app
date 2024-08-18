package com.K1ll4a.Server.commands;

import com.K1ll4a.Server.rulers.CollectionRuler;
import com.K1ll4a.Server.rulers.DatabaseRuler;
import com.K1ll4a.global.exeptions.NotFoundExeption;
import com.K1ll4a.global.facility.Mclass;
import com.K1ll4a.global.facility.Response;




import javax.xml.crypto.Data;
import java.sql.SQLException;

public class RemoveById  extends  Command{
    private final CollectionRuler collectionRuler;
    private final  DatabaseRuler databaseRuler;
    public RemoveById(CollectionRuler collectionRuler, DatabaseRuler databaseRuler){
        super("remove_by_id","удалить элемент из коллекции по его id");
        this.collectionRuler = collectionRuler;
        this.databaseRuler = databaseRuler;
    }

    @Override
    public Response apply(String[] arguments, Mclass mclass,String login,String password){
        if(arguments[1].isEmpty()){
            return new Response("Неправильное количество аргументов!\n" + "Использование: " + getName() + " '");
        }
        try{
            long deletableId = Long.parseLong(arguments[1]);
            var deletable = collectionRuler.byId(deletableId);
            if (deletable == null) throw new NotFoundExeption();
            var userID = collectionRuler.getUserid(login);
            var checkingUserID = collectionRuler.isCorrectID(deletableId);
            if(userID==checkingUserID){
                collectionRuler.removeMclassByIdDB(deletableId);
                collectionRuler.remove(deletable);
                return  new Response("Mclass удален");
            }else {
                return new Response("Mclass не удален! Возможные причины: вы пытались удалить чужой mclass");
            }
        }catch (NotFoundExeption e){
            return new Response("Элемент с таким id не найден");
        }catch (SQLException e){
            return new Response("Ошибка в базе данных при удалении mclass по его ID");
        }
    }

}
