package person.davino.dp.iterator.v4_composition;

import person.davino.dp.iterator.v2.Iterator;
import person.davino.dp.iterator.v4_composition.Menu;
import person.davino.dp.iterator.v3_composition.MenuComponent;

import java.util.Stack;

public class CompositIterator implements Iterator{

    Stack<Iterator> stack = new Stack<>();

    private Iterator iterator;

    public CompositIterator(Iterator iterator) {
        stack.push(iterator);
    }

    @Override
    public boolean hasNext() {
        if (stack.empty()) {
            return false;
        } else {
            Iterator iterator = stack.peek();
            if (!iterator.hasNext()) {
                stack.pop();
                return hasNext();
            } else {
                return true;
            }
        }
    }

    @Override
    public Object next() {
        if (hasNext()) {
            Iterator iterator = stack.peek();
            MenuComponent menuComponent = (MenuComponent)iterator.next();
            if (menuComponent instanceof Menu) { // push to stack
                stack.push(menuComponent.createIterator());
            }
            return menuComponent;
        } else { // not item
            return null;
        }
    }
}
