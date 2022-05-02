import React, {useState, useEffect, useId} from "react";
import Note from "../note/Note";
import './Board.css';



const Board = () => {

    const [notesList, setNotesList] = useState<Array<JSX.Element>>([]);

    useEffect(() => {
        
    });

    const deleteNote = () => {
            
    }

    // for test only 
    const initializeNotes = () => {
        notesList.push(<Note title="title" content="contentfdgmlksdfgj;lsdfkj;sdfgkljm;sdfglkfgjs;klsdfm;ldfksm;sdfglkm,gnmsdf;lfdgjmk;lskjlllljljljljljljljljljljljljljlljk;lkj;gldfaskj;fgaskjfg;sdlksdfgj;lksdfj;lsdfgkjm;lsdfkmsdfg;lksdfgml;kfg;lfgkdfgjk'dfglskfg;'sdlkfg;sdlkfdgk;lksdfgl;'fdgk;'ldfgk'dfg;slk"/>);
        notesList.push(<Note title="title" content="content"/>);
        notesList.push(<Note title="title" content="content"/>);
        notesList.push(<Note title="title" content="content"/>);
        notesList.push(<Note title="title" content="content"/>);
        notesList.push(<Note title="title" content="content"/>);
        notesList.push(<Note title="title" content="content"/>);
    }

    initializeNotes();

    return (
        <div className="board-container">
            <div className="board">
                {
                    React.Children.toArray(notesList)
                }
            </div>
        </div>
    );
}

export default Board;