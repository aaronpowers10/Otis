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

import java.util.ArrayList;
import java.util.Scanner;

public class TableParser implements Parser {

	private ArrayList<Parser> rowParsers;

	public TableParser(String fileName, TableRowFactory tableRowFactory) {
		rowParsers = new ArrayList<Parser>();
		Scanner tableInput;
		tableInput = new Scanner(getClass().getResourceAsStream(fileName));
		// tableInput = new BufferedReader(new InputStreamReader(new
		// FileInputStream(fileName)));
		while (tableInput.hasNextLine()) {
			rowParsers.add(tableRowFactory.create(tableInput.nextLine()));
		}
		tableInput.close();
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		for (Parser rowParser : rowParsers) {
			try {
				return rowParser.parse(in);
			} catch (CannotParseException e) {

			}
		}
		throw new CannotParseException();
	}

}
