import React,{useState,useEffect} from "react";

const EditUserForm = ({userToEdit,updateUser,cancelEdit})=>{
    const [name,setName] = useState('');
    const [email,setEmail] = useState('');

    useEffect(()=>{
        if(userToEdit){
            setName(userToEdit.name);
            setEmail(userToEdit.email);
        }


    },[userToEdit]);
    return(
        <div>
            {userToEdit ?(
                <from>
                    <h2>유저정보 수정하기</h2>
                    <label>
                        이름: <input type="text" value={name} onChange={e=>setName(e.target.value)} />
                        
                    </label>
                    <label>
                        이메일: <input type="email" value={email} onChange={e=>setEmail(e.target.value)} />

                    </label>
                <button>저장하기</button>
                <button>취소하기</button>

                </from>
            ): null}


        </div>
    )
}
export default EditUserForm;