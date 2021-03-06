/*
 *
 *  Copyright (C) 2017 Aaron Powers
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package otis.lexical;

public class InputToken {

	private int col;
	private int lineNumber;
	private char token;

	public InputToken(String token, int lineNumber, int col) {
		this.token = token.toCharArray()[0];
		//this.token[1] = token.toCharArray()[1];
		//this.token = token.toCharArray();
		this.lineNumber = lineNumber;
		this.col = col;
	}

	public int col() {
		return col;
	}

	public int lineNumber() {
		return lineNumber;
	}
	
	public char[] token() {
		return new char[] {token};
		//return new String(this.token);
		//return token;
	}

}
