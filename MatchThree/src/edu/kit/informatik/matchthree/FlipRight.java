package edu.kit.informatik.matchthree;

import java.util.Set;
import java.util.TreeSet;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

/**
 * Implements a move swapping a field and its right neighbor.
 * 
 * @author Frithjof Marquardt
 * @version 1.00, 25.02.2017
 */
public class FlipRight implements Move {
    private final Position pos;
    
    /**
     * Creates a new move that flips the field at position and the field on the right.
     * 
     * @param position the position to flip
     */
    public FlipRight(Position position) {
        pos = position;
    }

    @Override
    public boolean canBeApplied(Board board) {
        return (board.containsPosition(pos)) && (board.containsPosition(pos.plus(0, 1)));
    }

    @Override
    public void apply(Board board) throws BoardDimensionException {
        if (!canBeApplied(board)) throw new BoardDimensionException("Move cannot be applied to this board.");
        Token tokenA = board.getTokenAt(pos);
        board.setTokenAt(pos, board.getTokenAt(pos.plus(1, 0)));
        board.setTokenAt(pos.plus(1, 0), tokenA);
    }

    @Override
    public Move reverse() {
        return this;
    }

    @Override
    public Set<Position> getAffectedPositions(Board board) throws BoardDimensionException {
        if (!canBeApplied(board)) throw new BoardDimensionException("Move cannot be applied to this board.");
        TreeSet<Position> affected = new TreeSet<Position>();
        affected.add(pos);
        affected.add(pos.plus(1, 0));
        return affected;
    }
}