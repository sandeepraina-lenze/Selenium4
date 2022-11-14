package org.qa;

import  org.qa.service.nupano;

public class seleniumchrome {
    public static void main(String[] args) {
        nupano nupano = new nupano();

        nupano.clean("auto@test1.org", "auto");
        nupano.clean("auto@test2.org", "auto");
        nupano.clean("autoadmin@test1.org", "auto");
    }
}