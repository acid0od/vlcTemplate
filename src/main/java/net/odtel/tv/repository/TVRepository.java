package net.odtel.tv.repository;

import net.odtel.tv.model.ChannelList;
import net.odtel.tv.model.Server;

import java.util.List;

public interface TVRepository {
    List<ChannelList> getAllChannels();

    List<Server> getAllServers();
}
