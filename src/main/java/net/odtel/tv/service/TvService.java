package net.odtel.tv.service;

import net.odtel.tv.model.UserAuth;

public interface TvService {
    String getAllTVProgrammes();
    TokenResponse getToken(UserAuth userAuth);
}
