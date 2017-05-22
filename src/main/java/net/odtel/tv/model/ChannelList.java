package net.odtel.tv.model;

import lombok.Data;

@Data
public class ChannelList {
    private Long id;
    private String name;
    private String altName;
    private String streamAddress;
    private String url;
    private String aspect;


}
