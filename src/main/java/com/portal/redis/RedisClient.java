package com.portal.redis;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.portal.constant.Constant;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class RedisClient {
	public static JedisPool jedisPool;
	private static Log log = LogFactory.getLog(RedisClient.class);
	/**
	 * 最大链接数
	 */
	private static int maxActive = 1000;

	/**
	 * 最大空闲连结数
	 */
	private static int maxIdle = 20;

	/**
	 * 超时时间
	 */
	private static int maxWait = 3000;

	/**
	 * 端口
	 */
	private static int port = 6379;
	private static RedisClient redisInstance = null;

	public synchronized static RedisClient getInstance() {
		if (null == redisInstance) {
			redisInstance = new RedisClient(Constant.IP, maxActive, maxIdle,
					maxWait, port);
		}
		return redisInstance;
	}

	/**
	 * 初始Redis线程池
	 * 
	 * @param ip
	 * @param maxActive
	 * @param maxIdle
	 * @param maxWait
	 * @param port
	 */
	public RedisClient(String ip, int maxActive, int maxIdle, int maxWait,
			int port) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(maxActive);
		// 控制一个pool最多有多少个状态为idle的jedis实例
		config.setMaxIdle(maxIdle);
		// borrow一个jedis实例时，最大的等待时间
		config.setMaxWaitMillis(maxWait);
		jedisPool = new JedisPool(config, ip, port);
	}

	/**
	 * 向缓存中设置字符串内容
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            value
	 * @param seconds
	 * @return
	 * @throws Exception
	 */
	public static boolean setex(String key, int seconds, String value)
			throws Exception {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (seconds == 0) {
				jedis.set(key, value);
			} else {
				jedis.setex(key, seconds, value);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 根据key 获取内容
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		Jedis jedis = null;
		log.info("key:" + key);
		try {
			jedis = jedisPool.getResource();
			Object value = jedis.get(key);
			return value;
		} catch (Exception e) {
			log.info("error*************" + e.getMessage());
			e.printStackTrace();

			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 判断该key是否存在
	 * 
	 * @param key
	 * @return
	 */
	public static boolean exists(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 向缓存中设置对象
	 * 
	 * @param key
	 * @param seconds
	 * @param value
	 * @return
	 */
	public static boolean setClass(String key, int seconds, Object value) {
		Jedis jedis = null;
		try {
			String objectJson = JSON.toJSONString(value);
			jedis = jedisPool.getResource();
			if (seconds == 0) {
				jedis.set(key, objectJson);
			} else {
				jedis.setex(key, seconds, objectJson);
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 指定对象的数值+1
	 * 
	 * @param key
	 * @return
	 */
	public static long incr(String key, int add) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.incr(key);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 指定对象的数值-1
	 * 
	 * @param key
	 * @return
	 */
	public static long decr(String key, int add) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.decr(key);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 删除缓存中得对象，根据key
	 * 
	 * @param key
	 * @return
	 */
	public static boolean del(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(key);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 根据key 获取对象
	 * 
	 * @param key
	 * @return
	 */
	public static <T> T getClass(String key, Class<T> clazz) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String value = jedis.get(key);
			return JSON.parseObject(value, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 向缓存中设置集合
	 * 
	 * @param key
	 * @param seconds
	 * @param list
	 * @return
	 */
	public static <T> boolean setList(String key, int seconds, List<T> list) {
		Jedis jedis = null;
		try {
			String objectJson = JSON.toJSONString(list);
			jedis = jedisPool.getResource();
			jedis.setex(key, seconds, objectJson);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 根据key 获取List对象
	 * 
	 * @param key
	 * @param t
	 * @return
	 */
	public static <T> T getList(String key, TypeReference<T> t) {

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String value = jedis.get(key);
			return JSON.parseObject(value, t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 根据key 设置内容
	 * 
	 * @param key
	 * @param value
	 */
	public static void set(String key, String value) {
		Jedis jedis = null;
		log.info("key:" + key);
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
		} catch (Exception e) {
			log.info("error*************" + e.getMessage());
			e.printStackTrace();
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

}
