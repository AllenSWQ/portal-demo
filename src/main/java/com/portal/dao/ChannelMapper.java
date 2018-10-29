package com.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.portal.model.Channel;
import com.portal.model.ChannelDetail;
import com.portal.model.ChannelFile;

@Repository
public interface ChannelMapper extends BaseMapper<Channel> {

	int getChannelByChannel(@Param("channel") String channel);
	
	List<ChannelDetail> getDetailByChannelID(@Param("search") String search,
			@Param("offset") Integer offset, @Param("limit") Integer limit,
			@Param("sort") String sort, @Param("order") String order,
			@Param("params") Map<String, Object> params);

	int getDetailCount(@Param("search") String search,
			@Param("params") Map<String, Object> params);
	
	int updateChannelDetail(ChannelDetail channelDetail);
	
	int updateChannelDetailState(ChannelDetail channelDetail);
	
	int addChannelDetail(ChannelDetail channelDetail);
	
	int getChannelDetailByChannel(@Param("channel") String channel);
	
	List<Channel> getSelect(@Param("brand_id") String brand_id, @Param("mul_uid") String mul_uid);
	
	List<ChannelFile> getFileByID(@Param("channel_id") Integer channel_id);
	
	int addChannelFile(ChannelFile channelFile);
	
	int updateChannelFileState(@Param("id") Integer id);

}