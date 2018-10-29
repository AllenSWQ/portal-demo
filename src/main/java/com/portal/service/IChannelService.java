package com.portal.service;

import java.util.List;

import com.portal.model.Channel;
import com.portal.model.ChannelDetail;
import com.portal.model.ChannelFile;


public interface IChannelService {

	public List<Channel> getAll(String search, Integer offset, Integer limit,
			String sort, String order, String filter, String mul_uid) throws Exception;

	int getCount(String search, String filter, String mul_uid) throws Exception;
	
	public int addChannel(Channel channel) throws Exception;
	
	int getChannelByChannel(String channel) throws Exception;
	
	int deleteChannel(Integer id) throws Exception;
	
	public List<ChannelDetail> getDetailByChannelID(String search, Integer offset, Integer limit,
			String sort, String order, String filter, Integer channel_id) throws Exception;

	int getDetailCount(String search, String filter, Integer channel_id) throws Exception;
	
	int updateChannelDetail(ChannelDetail channelDetail) throws Exception;
	
	int updateChannelDetailState(ChannelDetail channelDetail) throws Exception;
	
	int addChannelDetail(ChannelDetail channelDetail) throws Exception;
	
	int getChannelDetailByChannel(String channel) throws Exception;
	
	public List<Channel> getSelect(String brand_id, String mul_uid) throws Exception;
	
	public List<ChannelFile> getFileByID(Integer channel_id) throws Exception;
	
	int addChannelFile(ChannelFile channelFile) throws Exception;
	
	int deleteChannelFile(Integer id) throws Exception;

}
