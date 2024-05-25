import { useEffect, useState } from "react";

export const Rectangular = ({type, data}) => {
    const [color, setColor] = useState('#eaedf3');


    useEffect(()=>{
        if (data != null) {
            setColor(data.category.color);
        }
        
    }, [])
    
    return (
        <div id="rectangular" style={{backgroundColor: color}}>
            {type == "plan" ? <div className="category">{data.category.name}</div> : null}

            {type == "plan" ? <div className="title">{data.title}</div> : "+ 할 일을 추가하세요"}
        </div>
    );
}