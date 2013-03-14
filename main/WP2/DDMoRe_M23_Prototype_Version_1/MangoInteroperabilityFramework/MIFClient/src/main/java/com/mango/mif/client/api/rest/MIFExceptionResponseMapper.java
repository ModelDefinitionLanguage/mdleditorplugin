/* File			:  MIFExceptionResponseMapper.java
 * Project		:  MIFServer
 * Created on	:  10 Jul 2012
 */

package com.mango.mif.client.api.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.client.ResponseExceptionMapper;

import com.mango.mif.exception.MIFException;

/**
 * @version $Revision: $ as of $Date: $
 *          <p>
 *          SVN Entry : $HeadURL: $
 *          <p>
 *          SVN ID : $Id: $
 *          <p>
 *          Last edited by : $Author: $
 *          
 *          Unmarshalls MIFExceptoin from a Response
 */
public class MIFExceptionResponseMapper implements
		ResponseExceptionMapper<MIFException> {
    private final static String ERROR_TAG = "ETag";
	@Override
	public MIFException fromResponse(Response response) {
		StringBuilder msg = new StringBuilder();
		if (response != null) {
			if (response.getMetadata() != null
					&&  response.getMetadata().get(ERROR_TAG)!= null) {
				msg.append(retrieveErrorMessage(response.getMetadata().get(ERROR_TAG)));
			} else {
				msg.append("Technical Failure: ");
				msg.append(response.getStatus());
			}
		}
		return new MIFException(msg.toString());
	}

    private String retrieveErrorMessage(List<Object> tags) {
        StringBuilder msg = new StringBuilder();
        for (Object tagObject : tags) {
            String tag = tagObject.toString();
            tag = StringUtils.trimToEmpty(tag);
            if(tag.startsWith("\"") && tag.endsWith("\"")) {
                tag = tag.substring(1, tag.length()-1);
            }
            msg.append(tag);
        }
        return msg.toString();
    }

}
