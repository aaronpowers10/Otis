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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileLink implements MoreCharactersSource {

	private BufferedReader in;
	private int nextChar;

	public FileLink(String fileName) {
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			nextChar = in.read();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public String nextChar() {
		String nextCharString = null;
		if (nextChar == -1) {
			throw new EndOfSequenceException("The end of the file was reached.");
		} else {
			nextCharString = Character.toString((char) nextChar);
		}
		try{
			nextChar = in.read();
		} catch(IOException e){
			throw new EndOfSequenceException("The end of the file was reached.");
		}
		return nextCharString;
	}

	@Override
	public void close(){
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean hasNext() {
		if(nextChar == -1){
			return false;
		} else {
			return true;
		}
	}

}
