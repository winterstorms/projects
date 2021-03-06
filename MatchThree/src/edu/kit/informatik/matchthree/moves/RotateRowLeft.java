package edu.kit.informatik.matchthree.moves;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

/**
 * Implements a move that rotates a row left.
 * 
 * @author Frithjof Marquardt
 * @version 1.00, 25.02.2017
 */
public class RotateRowLeft implements Move {
    private final int row;
    
    /**
     * Creates a new move that rotates the provided row left.
     * 
     * @param rowIndex the index of the row to rotate
     * @throws BoardDimensionException if rowIndex specifies row with negative index
     */
    public RotateRowLeft(int rowIndex) throws BoardDimensionException {
        if (rowIndex < 0) throw new BoardDimensionException("Rows with a negative index do not exist on any board.");
        row = rowIndex;
    }

    @Override
    public boolean canBeApplied(Board board) {
        Objects.requireNonNull(board);
        return (board.getRowCount() > row);
    }

    @Override
    public void apply(Board board) throws BoardDimensionException {
        if (!canBeApplied(board)) throw new BoardDimensionException("Move cannot be applied to this board.");
        Token tokenA = board.getTokenAt(Position.at(0, row));
        for (int i = 0; i < board.getColumnCount() - 1; i++) {
            board.setTokenAt(Position.at(i, row), board.getTokenAt(Position.at(i + 1, row)));
        }
        board.setTokenAt(Position.at(board.getColumnCount() - 1, row), tokenA);
    }

    @Override
    public Move reverse() {
        return new RotateRowRight(row);
    }

    @Override
    public Set<Position> getAffectedPositions(Board board) throws BoardDimensionException {
        if (!canBeApplied(board)) throw new BoardDimensionException("Move cannot be applied to this board.");
        Set<Position> affected = new HashSet<Position>();
        for (int i = 0; i < board.getColumnCount(); i++) {
            affected.add(Position.at(i, row));
        }
        return affected;
    }
}