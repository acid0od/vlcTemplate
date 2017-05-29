package net.odtel.tv.service;

import lombok.extern.slf4j.Slf4j;
import net.odtel.tv.configuration.Config;
import net.odtel.tv.model.ChannelList;
import net.odtel.tv.model.Server;
import net.odtel.tv.model.UserAuth;
import net.odtel.tv.repository.TVRepository;
import net.odtel.tv.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class TvProgrammeService implements TvService {
    @Autowired
    private TVRepository tvRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private Config config;
    
    @Override
    public TokenResponse getToken(UserAuth userAuth) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(jwtTokenUtil.CLAIM_KEY_USERNAME, userAuth.getUserName());
        claims.put(jwtTokenUtil.CLAIM_KEY_CREATED, new Date());
        return new TokenResponse(jwtTokenUtil.generateToken(claims));
    }
    
    @Override
    public String getAllTVProgrammes() {
        Random randomGenerator = new Random();

        StringBuffer buffer = new StringBuffer();

        List<ChannelList> all = tvRepository.getAllChannels();
        List<Server> allServers = tvRepository.getAllServers();
        //buffer.append("#EXTM3U url-tvg=\"http://61.50.196.42/iptv/jtv.zip\" deinterlace=1 tvg-shift=-1\n");
        buffer.append(config.getTitle());

        for (ChannelList channelList : all) {
            Server server = allServers.get(randomGenerator.nextInt(allServers.size()));

            buffer.append("#EXTINF:-1 tvg-name=")
                    .append(channelList.getAltName())
                    .append(", ")
                    .append(channelList.getName())
                    .append("\n");

    /*        buffer.append("http://195.138.161.30:9896/http/").append(server.getIpChina())
                    .append(":")
                    .append(server.getPort())
                    .append("/udp/")
                    .append(channelList.getStreamAddress())
                    .append("\n");*/
    
            buffer.append(config.getPrefix()).append("/")
                    .append(channelList.getStreamAddress())
                    .append("\n");
        }

        return buffer.toString();
    }
}
