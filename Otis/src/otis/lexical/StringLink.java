/*
 *
 *  Copyright (C) 2018 Aaron Powers
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

import java.util.Scanner;

public class StringLink implements MoreCharactersSource {

	private Scanner in;

	public StringLink(String string) {
		in = new Scanner(string);
		in.useDelimiter("");
	}

	@Override
	public String nextChar() {
		if (in.hasNext()) {
			return in.next();
		} else {
			throw new EndOfSequenceException("The end of the file was reached.");
		}
	}

	@Override
	public void close() {
		in.close();

	}

	@Override
	public boolean hasNext() {
		return in.hasNext();
	}

}
