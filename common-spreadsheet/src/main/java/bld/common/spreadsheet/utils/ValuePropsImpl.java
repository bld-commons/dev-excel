/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.utils.ValuePropsImpl.java
*/
package bld.common.spreadsheet.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class ValuePropsImpl.
 */
@Component
public class ValuePropsImpl implements ValueProps {
	
	/** The env. */
	@Autowired
	private AbstractEnvironment env;
	
	/**
	 * Value props.
	 *
	 * @param props the props
	 * @return the string
	 */
	@Override
	public String valueProps(String props) {
		String keyProperties=props;
		if(StringUtils.isNotBlank(props) && props.startsWith("${") && props.endsWith("}")) 
			keyProperties=this.env.resolvePlaceholders(keyProperties);
//			keyProperties=props.substring(2);
//			keyProperties=keyProperties.substring(0, keyProperties.length() - 1);
//			keyProperties=this.env.getProperty(keyProperties, props);
		
		return keyProperties;
	}

}
