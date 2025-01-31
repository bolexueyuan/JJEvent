/**
 * Copyright (c) 2012-2013, Michael Yang 杨福海 (www.yangfuhai.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.bolexueyuan.android.analytics.analytics.db.exception;

import com.github.bolexueyuan.android.analytics.analytics.exception.EventException;

public class DbException extends EventException {
	private static final long serialVersionUID = 1L;
	
	public DbException() {}
	
	
	public DbException(String msg) {
		super(msg);
	}
	
	public DbException(Throwable ex) {
		super(ex);
	}
	
	public DbException(String msg, Throwable ex) {
		super(msg,ex);
	}
	
}
