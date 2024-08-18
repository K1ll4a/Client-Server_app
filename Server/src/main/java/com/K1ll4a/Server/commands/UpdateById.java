package com.K1ll4a.Server.commands;



import com.K1ll4a.Server.rulers.CollectionRuler;
import com.K1ll4a.Server.rulers.DatabaseRuler;
import com.K1ll4a.global.exeptions.NotFoundExeption;
import com.K1ll4a.global.facility.Response;
import com.K1ll4a.global.facility.Mclass;

import java.sql.SQLException;

public class UpdateById  extends Command {
    private final CollectionRuler collectionRuler;
    private final DatabaseRuler databaseRuler;

    public UpdateById(CollectionRuler collectionRuler, DatabaseRuler databaseRuler) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionRuler = collectionRuler;
        this.databaseRuler = databaseRuler;
    }

    @Override
    public Response apply(String[] arguments, Mclass mclass, String login, String password) {
        if (arguments[1].isEmpty()) {
            return new Response("Неправильное количество аргументов!\n" + "Использование: " + getName() + " '");

        }
        try {
            long deletableId = Long.parseLong(arguments[1]);
            var deletable = collectionRuler.byId(deletableId);
            if (deletable == null) throw new NotFoundExeption();
            var userID = collectionRuler.getUserid(login);
            var checkingUsertID = collectionRuler.isCorrectID(deletableId);
            if (userID == checkingUsertID && mclass != null && mclass.validate()) {
                collectionRuler.updateMclassDB(mclass, deletableId);
                collectionRuler.updateCollection(deletable, mclass, deletableId, login);
                return new Response("Mclass обновлен");
            } else {
                return new Response("Mclass не обновлён! Возможные причины: вы попытались обновить чужой mclass" +
                        "или поля обновлённого mclass невалидны");
            }
        } catch (NotFoundExeption e) {
            return new Response("Продукта с таким ID не существует в коллекции");
        } catch (SQLException e) {
            return new Response("Ошибка обновления mclass в базе данных");
        }
    }
}
