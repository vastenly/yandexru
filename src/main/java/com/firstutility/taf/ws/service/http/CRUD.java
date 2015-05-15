package com.firstutility.taf.ws.service.http;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;

public enum CRUD {
	CREATE {
		@Override
		public PostMethod getHandler(String url) {
			return new PostMethod(url);
		}
	},
	READ {
		@Override
		public GetMethod getHandler(String url) {
			return new GetMethod(url);
		}
	},
	UPDATE {
		@Override
		public PutMethod getHandler(String url) {
			return new PutMethod(url);
		}
	},
	DELETE {
		@Override
		public DeleteMethod getHandler(String url) {
			return new DeleteMethod(url);
		}
	};

	public abstract HttpMethod getHandler(String url);
}
