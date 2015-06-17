package com.firstutility.taf.ws.service.http;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;

public enum CRUD {
	CREATE {
		@Override
		public HttpPost getHandler(String url) {
			return new HttpPost(url);
		}
	},
	READ {
		@Override
		public HttpGet getHandler(String url) {
			return new HttpGet(url);
		}
	},
	UPDATE {
		@Override
		public HttpPut getHandler(String url) {
			return new HttpPut(url);
		}
	},
	DELETE {
		@Override
		public HttpDelete getHandler(String url) {
			return new HttpDelete(url);
		}
	};

	public abstract HttpRequestBase getHandler(String url);
}
