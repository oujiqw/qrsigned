package util;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	/**
	 * 用于将JSON转换为对应的实体对象
	 * @param str JSON的字符串格式
	 * @param clazz 所要转的类型
	 * @return
	 */
	private static ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * @Title: jsonToBean
	 * @Description: 用于将json转换成对应的bean
	 * @param str
	 * @param clazz
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T jsonToBean(String str,Class<T> clazz) throws JsonParseException, JsonMappingException, IOException
	{
		return mapper.readValue(str,clazz);
	}
	
	/**
	 * @Title: beanToJson
	 * @Description: 用于将bean转换成对应的json
	 * @param obj
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String beanToJson(Object obj) throws JsonProcessingException
	{
		return mapper.writeValueAsString(obj);
	}
	
	/**
	 * @Title: writeToResponse
	 * @Description: 用于将对象写成json
	 * @param obj
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static void writeToResponse(Object obj) throws JsonGenerationException, JsonMappingException, IOException
	{
		mapper.writeValue(ServletActionContext.getResponse().getOutputStream(), obj);
	}
}
