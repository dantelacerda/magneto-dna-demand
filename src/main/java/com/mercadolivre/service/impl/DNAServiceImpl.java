package com.mercadolivre.service.impl;

import java.util.regex.Pattern;

import com.mercadolivre.exception.InputValidationException;
import com.mercadolivre.service.DnaService;

/***
 *  Classe responsável pela validação de DNA
 *
 */
public class DNAServiceImpl implements DnaService {
	
	private char[][] dnaSecuence;
	private static final int SEQ_TO_CONSIDERER_MUTANT = 2; 
	private static final int SEQ_LENGTH = 4; 
	private int sequenceCount = 0; 
	
	public enum ReadDirections {
	    HORIZONTAL, VERTICAL 
	}
	
	public enum DiagonalReadDirections {
		FROM_RIGHT, FROM_LEFT 
	}
	
	public enum DiagonalReadType {
		BELOW_MAIN_DIAGONAL, ABOVE_MAIN_DIAGONAL_INCLUDING
	}
	
	/**
	 * Método para ler linhas retas.
	 * 
	 * @param Direção(Horizontal ou Vertical)
	 * @param startChar primeira letra
	 * @param index Índice da coluna a ser lido
	 * 
	 * @return true quando for mutante
	 */
	private boolean readHorizontalOrVertical(ReadDirections direction, char startChar, int index) {
		
		int sequenceMatchCharactersCount = 1; 
		char lastCharacter = startChar;
		char currentCharacter;
		for (int subindex = 1; dnaSecuence.length - subindex  + sequenceMatchCharactersCount >= SEQ_LENGTH 
			&&	subindex < dnaSecuence.length; subindex++) {
			
			currentCharacter = (ReadDirections.HORIZONTAL.equals(direction) ? dnaSecuence[index][subindex] : dnaSecuence[subindex][index]);
			if(lastCharacter == currentCharacter) {
				sequenceMatchCharactersCount++;
				if(sequenceMatchCharactersCount == SEQ_LENGTH) {
					sequenceCount++;
					sequenceMatchCharactersCount = 0;
					if(sequenceCount == SEQ_TO_CONSIDERER_MUTANT) { 
						return true;
					}	
				}
			} else {
				lastCharacter = currentCharacter;
				sequenceMatchCharactersCount = 1;
			}
		}
		
		return false;
	}
	
	/***
	 * Método para verificar as diagonais
	 *
	 * @param leftOrRight A direção a ser buscada
	 * @param aboveOrBelow Acima ou abaixo
	 * @param baseN linha de início
	 * @param baseM coluna de início
	 * 
	 * @return true quando for mutante
	 */
	private boolean readDiagonals(DiagonalReadDirections leftOrRight, DiagonalReadType aboveOrBelow, int baseN, int baseM) {
		
		int offset = 1;
		
		int sequenceMatchCharactersCount = 1;	
		
		char lastCharacter = dnaSecuence[baseN][baseM];
		char currentCharacter;
		
		boolean bottomReadCondition = baseN + offset < dnaSecuence.length;
				
		boolean topReadCondition = (leftOrRight.equals(DiagonalReadDirections.FROM_LEFT) && baseM + offset < dnaSecuence.length ||
				   					leftOrRight.equals(DiagonalReadDirections.FROM_RIGHT)  && baseM - offset >= 0);
		
		while ((aboveOrBelow.equals(DiagonalReadType.ABOVE_MAIN_DIAGONAL_INCLUDING) && topReadCondition) ||
			   (aboveOrBelow.equals(DiagonalReadType.BELOW_MAIN_DIAGONAL) && bottomReadCondition)) {
			
			currentCharacter = (leftOrRight.equals(DiagonalReadDirections.FROM_LEFT)) ? dnaSecuence[baseN + offset][baseM + offset]:
																			            dnaSecuence[baseN + offset][baseM - offset];
			if(lastCharacter == currentCharacter) {
				sequenceMatchCharactersCount++;
				if(sequenceMatchCharactersCount == SEQ_LENGTH) {
					sequenceCount++;
					sequenceMatchCharactersCount = 0;
					if(sequenceCount >= SEQ_TO_CONSIDERER_MUTANT) { 
						return true;
					}	
				}
			} else {
				lastCharacter = currentCharacter;
				sequenceMatchCharactersCount = 1;
			}
			
			offset++;
			
			bottomReadCondition = baseN + offset < dnaSecuence.length;
			
			topReadCondition = (leftOrRight.equals(DiagonalReadDirections.FROM_LEFT) && baseM + offset < dnaSecuence.length ||
					   					   leftOrRight.equals(DiagonalReadDirections.FROM_RIGHT)  && baseM - offset >= 0);
		}
		return false;
	}
	
	/***
	 * Analisa se é mutante ou humano
	 * 
	 * @return true Se for mutante e false se for humano
	 */
	public boolean isMutant(String[] dna) throws InputValidationException{
		
		char lastCharacter;
		
		dnaSecuence = populateDnaSecuence(dna);
		if(dnaSecuence.length < 4) {
			return false;
		}
		
		// Horizontal read
		for (int row = 0; row < dnaSecuence.length; row++) {
			lastCharacter = dnaSecuence[row][0];
			if(readHorizontalOrVertical(ReadDirections.HORIZONTAL, lastCharacter, row) == true) {
				return true;
			}	
		}
		// Vertical read
		for (int col = 0; col < dnaSecuence.length; col++) {
			lastCharacter = dnaSecuence[0][col];
			if(readHorizontalOrVertical(ReadDirections.VERTICAL, lastCharacter, col) == true) {
				return true;
			}
		}
		// Bottom diagonals, from left, not including main diagonal
		for (int row = 1; row < dnaSecuence.length; row++) {
			lastCharacter = dnaSecuence[row][0];
			if(readDiagonals(DiagonalReadDirections.FROM_LEFT, DiagonalReadType.BELOW_MAIN_DIAGONAL, row, 0) == true) {
				return true;
			}	
			
		}
		// Bottom diagonals, from right, not including main diagonal
		for (int row = 1; row < dnaSecuence.length; row++) {
			lastCharacter = dnaSecuence[row][dnaSecuence.length - 1];
			if(readDiagonals(DiagonalReadDirections.FROM_RIGHT, DiagonalReadType.BELOW_MAIN_DIAGONAL, row, dnaSecuence.length - 1) == true) {
				return true;
			}	
		}
		// Top diagonals, from left, including main diagonal
		for (int col = 0; col < dnaSecuence.length; col++) {
			lastCharacter = dnaSecuence[0][col];
			if(readDiagonals(DiagonalReadDirections.FROM_LEFT, DiagonalReadType.ABOVE_MAIN_DIAGONAL_INCLUDING, 0, col) == true) {
				return true;
			}	
			
		}
		// Top diagonals, from right, including main diagonal
		for (int col = 1; col < dnaSecuence.length; col++) {
			lastCharacter = dnaSecuence[0][dnaSecuence.length - col];
			if(readDiagonals(DiagonalReadDirections.FROM_RIGHT,  DiagonalReadType.ABOVE_MAIN_DIAGONAL_INCLUDING,  0, dnaSecuence.length - col) == true) {
				return true;
			}		
		}	
		return false;
	}
	
	/**
	 * Popula um Array com o DNA e no final verifica se está correto
	 * 
	 * @param dna O DNA pasado
	 * @return Um Array de Char com o DNA
	 * 
	 */
	private char[][] populateDnaSecuence(String[] dna) throws InputValidationException{
		
		int dnaSecuenceRange = dna.length;
		Pattern pattern = Pattern.compile("[atcg]+", Pattern.CASE_INSENSITIVE);
		
		dnaSecuence = new char[dnaSecuenceRange][dnaSecuenceRange];
		
		for (int range = 0; range < dnaSecuenceRange ; range++) {
			
				if(dna[range].length() != dnaSecuenceRange) {
					throw new InputValidationException("The length of every DNA secuences has to be equal to the number of secuences");
				} else if(!pattern.matcher(dna[range]).matches()){
					throw new InputValidationException("The characters provided in the secuence are invalid. The only valid characters are A, T, G and C.");
				} else {
					dnaSecuence[range] = dna[range].toUpperCase().toCharArray();
				}
		}

		return dnaSecuence;
	}
}
