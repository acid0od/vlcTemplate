package net.odtel.tv.repository;

import net.odtel.tv.model.ChannelList;
import net.odtel.tv.model.Server;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import javax.xml.ws.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


public class ChannelListRepository implements TVRepository {

    private JdbcTemplate jdbcTemplate;

    public ChannelListRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<ChannelList> getAllChannels() {
        String sql = "SELECT id, ch_name, ch_altname, stream_address, aspect from chanel_list where not_working=0 and ch_status=1";

/*        List<ChannelList> channelLists = jdbcTemplate.query(sql, new RowMapper<ChannelList>() {

            @Override
            public ChannelList mapRow(ResultSet rs, int rowNum) throws SQLException {
                ChannelList channelList = new ChannelList();

                channelList.setId(rs.getLong("id"));
                channelList.setName(rs.getString("ch_name"));
                channelList.setAltName(rs.getString("ch_altname"));
                channelList.setStreamAddress(rs.getString("stream_address"));
                channelList.setAspect(rs.getString("aspect"));

                return channelList;
            }

        });*/

        ChannelList channelList = new ChannelList();
        channelList.setAspect("aspect");
        channelList.setName("name");
        channelList.setStreamAddress("streamAddress");
        channelList.setId(11L);
        channelList.setUrl("url");
        return Collections.singletonList(channelList);
        //return channelLists;
    }

    @Override
    public List<Server> getAllServers() {
        String sql = "select ip, port from bonus_servers where unicast=1 and status=1";

  /*      List<Server> servers = jdbcTemplate.query(sql, new RowMapper<Server>() {
            @Override
            public Server mapRow(ResultSet resultSet, int i) throws SQLException {
                Server server = new Server();
                server.setIpChina(resultSet.getString("ip"));
                server.setPort(resultSet.getString("port"));
                return server;
            }
        });
*/
        Server server = new Server();
        server.setPort("2222");
        server.setIpChina("192.168.0.1");
        return Collections.singletonList(server);
        //return servers;
    }
}
