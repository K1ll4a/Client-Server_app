package com.K1ll4a.Server.commands;


import com.K1ll4a.global.facility.Mclass;
import com.K1ll4a.global.facility.Response;
import com.K1ll4a.Server.rulers.CommandRuler;



/**
 * команда выводящая все доступные команды
 */
public class Help extends Command {
    private final CommandRuler commandRuler;

    public Help( CommandRuler commandRuler) {
        super("help", "вывести справку по доступным командам");
        this.commandRuler = commandRuler;
    }
    /**
     * метод выполняет команду
     *
     * @return возвращает сообщение о  успешности выполнения команды
     */
    @Override
    public Response apply(String[] arguments , Mclass mclass,String login,String password) {
        if (!arguments[1].isEmpty()) {
            //console.println("Неправильное количество аргументов!");
            //console.println("Использование: '" + getName() + "'");
            return new Response("Неправильное количество аргументов!\nИспользование: '\" + getName() + \"'");
        }

/*        commandRuler.getCommands().values().forEach(command -> {
            console.printTable(command.getName(), command.getDescription());
        });*/

        StringBuilder result = new StringBuilder();
        commandRuler.getCommands().values().forEach(command -> {
            result.append(command.getName() + " : " + command.getDescription()+"\n\n");
        });
        return new Response(result.toString());
    }
}
