package com.K1ll4a.Server.commands;

import com.K1ll4a.global.facility.Response;
import com.K1ll4a.global.facility.Mclass;


import java.sql.SQLException;

public interface Executable {
    Response apply(String[] arguments , Mclass mclass,String login,String password) throws SQLException;
}