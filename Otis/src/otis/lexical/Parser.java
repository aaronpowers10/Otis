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

import java.util.ArrayList;
import java.util.Scanner;

public interface Parser {

	public String parse(InputSequence in) throws CannotParseException;
	
	public default String parseWithMessage(InputSequence in, ArrayList<UpdateListener> updateListeners) throws CannotParseException {
		String result = parse(in);
		for(UpdateListener listener: updateListeners){
			listener.update(message());
		}
		return result;
	}
	
	public default String message(){
		return "";
	}
	
	public default InputSequence parseAsInputSequence(InputSequence in) throws CannotParseException {
		InputSequence resultSequence = new InputSequence(in.maxColumn());
		String parserResult = parse(in);
		Scanner scanner = new Scanner(parserResult);
		scanner.useDelimiter("");
		while (scanner.hasNext()) {
			resultSequence.addChar(scanner.next());
		}
		scanner.close();

		return resultSequence;

	}

}
