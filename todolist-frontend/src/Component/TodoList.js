import React, {useState, useContext} from "react";
import TodoListContext from "./TodoListContext";

const TodoList = () => {

    const {todoList, setTodoList, loginMember} = useContext(TodoListContext);

    const {inputTodo, setInputTodo} = useState('');

    let keyIndex = 0;

    // 할 일 추가버튼 기능 설정
     const 할일추가버튼 = () => {

        //만약에 할 일이 입력되지 않았다면 입력해달라는 알람창 띄워주기
        if (inputTodo.trim().length === 0) { //trim() = 앞 뒤 공백 제거 스페이스바 거부
            alert('할 일을 입력해주세요.');
            return;
        }

        fetch("/todo", { //TodoController에서 /todo 라는 URL 주소에서 DB에 값 추가하기
            method: "post",
            headers: {'Content-Type' : 'application/json'},
            body : JSON.stringify({
                title:inputTodo,
                todoMemberNo:loginMember.todoMemberNo
            })
        })
        .then(response => response.text())
        .then(todoNo => {

            if(Number(todoNo) === 0) { // 실패시 멈춤
                return;
            }
            /*
                기존 todoList + 새로 추가된 Todo를 이용해 새 배열을 만들어 todoList 대입
            */
           //새로 추가된 Todo 만들기
           const newTodo = {
            todoNo:todoNo,
            title:inputTodo,
            isDone:'O',
            todoMemberNo:loginMember.todoMemberNo
           };

           // 기존 todoList + newTodo 를 이용해서 새로운 할 일 목록을 만들기
           // 기존에 있던 할 일 목록과 새로 등록할 할 일 목록 합치기
           const newTodoList = [...todoList, newTodo];

           // todoList에 대입
           setTodoList(newTodoList);
           setInputTodo(''); //기존에 작성된 input값 비워주기
        })
        //문제가 생기면 문제 console창에서 보여주기
        .catch(
            e =>console.log(e)
        );
     }

     // O,X 업데이트 할 일을 완료하면 X버튼이 표시, 할 일을 완료하지 못하면 O버튼이 표시
     //             할 일을 처음부터 끝까지 모두 완료 미완료 처리하는 것이 아니라
     //             특정 할 일과 그 할 일의 번호를 받아 특정 할 일만 수정
     const 할일수정버튼 = (todo, index) => {
        console.log("todo가 뭐지 ? ", todo);
        console.log("index가 뭘까요 ?", index);

        fetch("/todo", {
            method: "put",
            // headers에서 
            // Content-Type는 소비자가 Controller로 값을 전달할 때 
            // 이 값이 이미지, 동영상, 글자 등 어떤 파일인지 전달하는 공간
            headers:{'Content-Type' : 'application/json'},
            // JSON으로 된 파일을 글자로 변경해서 글자 취급으로 사용
            body: JSON.stringify({
                todoNo: todo.todoNo,
                isDone : todo.isDone === 'O' ? 'X' : 'O' 
                /* 
                만약에  
                삼항연산자   조건이    ? true일 때 실행할 구문 : 조건이 false일 때 실행할 구문 
                            todo.isDone === 'O'  ? 'X' 
                할일 완료 여부에 O로 표시되어 있으면  X로 글자 변경
                            todo.isDone === 'O'  :  todo.isDone이 X라면 이라는 표기
                            todo.isDone === 'O'  : 'O' 
                할일 완료 여부에 로 표시되어 있으면   O로 글자 변경
                */
            })
        })

     }

     const 할일삭제버튼 = () => {

     }

    return (
        <>
            <h1>{loginMember.name}의 Todo List</h1>
            <div className="todo-container">
                <h3>할 일(Todo) 입력</h3>
                <div>
                    <input 
                    type="text" 
                    value={inputTodo} 
                    onChange={ e => setInputTodo(e.target.value)} />
                    <button onClick={할일추가버튼}>할 일  추가하기</button>
                </div>

                <ul>
                    {/*배열.map : 기존 배열을 이용해서 새로운 배열 만들기 */}
                    {TodoList.map((todo, index) => (
                        <li key={keyIndex++}>
                            <div>
                                <span className={ todo.isDone === 'X' ? 'todo-compleate' : ''}>
                                    {todo.title}
                                </span>

                                <span>
                                    <button onClick={() => {할일수정버튼(todo, index)}}>
                                        {todo.isDone}
                                    </button>
                                    <button onClick={() => {할일삭제버튼(todo.todoNo, index)}}>
                                        삭제
                                    </button>
                                </span>
                            </div>

                        </li>
                    ))}

                </ul>
            </div>
        </>
    )
}
export default TodoList;