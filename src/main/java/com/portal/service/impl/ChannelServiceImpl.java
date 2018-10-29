package com.portal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.portal.dao.ChannelMapper;
import com.portal.model.Channel;
import com.portal.model.ChannelDetail;
import com.portal.model.ChannelFile;
import com.portal.service.IChannelService;
import com.alibaba.druid.util.StringUtils;

@Service("channelService")
public class ChannelServiceImpl implements IChannelService {

	@Resource
	private ChannelMapper channelMapper;

	@Override
	public List<Channel> getAll(String search, Integer offset, Integer limit,
			String sort, String order, String filter, String mul_uid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		return channelMapper.getAll(search, offset, limit, sort, order, params);
	}

	@Override
	public int getCount(String search, String filter, String mul_uid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(mul_uid)) {
			params.put("mul_uid", mul_uid);
		}
		return channelMapper.getCount(search, params);
	}
	
	@Override
	public int addChannel(Channel channel) throws Exception {
		// TODO Auto-generated method stub
		return channelMapper.insertSelective(channel);
	}

	@Override
	public int getChannelByChannel(String channel) throws Exception {
		// TODO Auto-generated method stub
		return channelMapper.getChannelByChannel(channel);
	}

	@Override
	public int deleteChannel(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return channelMapper.deleteByID(id);
	}

	@Override
	public List<ChannelDetail> getDetailByChannelID(String search, Integer offset,
			Integer limit, String sort, String order, String filter, Integer channel_id)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("channel_id", channel_id);
		return channelMapper.getDetailByChannelID(search, offset, limit, sort, order, params);
	}

	@Override
	public int getDetailCount(String search, String filter, Integer channel_id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("channel_id", channel_id);
		return channelMapper.getDetailCount(search, params);
	}

	@Override
	public int updateChannelDetail(ChannelDetail channelDetail)
			throws Exception {
		// TODO Auto-generated method stub
		return channelMapper.updateChannelDetail(channelDetail);
	}
	
	@Override
	public int updateChannelDetailState(ChannelDetail channelDetail)
			throws Exception {
		// TODO Auto-generated method stub
		return channelMapper.updateChannelDetailState(channelDetail);
	}

	@Override
	public int addChannelDetail(ChannelDetail channelDetail) throws Exception {
		// TODO Auto-generated method stub
		return channelMapper.addChannelDetail(channelDetail);
	}

	@Override
	public int getChannelDetailByChannel(String channel) throws Exception {
		// TODO Auto-generated method stub
		return channelMapper.getChannelDetailByChannel(channel);
	}

	@Override
	public List<Channel> getSelect(String brand_id, String mul_uid) throws Exception {
		// TODO Auto-generated method stub
		return channelMapper.getSelect(brand_id, mul_uid);
	}

	@Override
	public List<ChannelFile> getFileByID(Integer channel_id) throws Exception {
		// TODO Auto-generated method stub
		return channelMapper.getFileByID(channel_id);
	}

	@Override
	public int addChannelFile(ChannelFile channelFile) throws Exception {
		// TODO Auto-generated method stub
		return channelMapper.addChannelFile(channelFile);
	}

	@Override
	public int deleteChannelFile(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return channelMapper.updateChannelFileState(id);
	}
	
}
