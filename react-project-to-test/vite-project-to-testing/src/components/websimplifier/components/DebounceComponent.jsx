import { useState } from "react"
import useDebounce from "../hooks/useDebounce"

export default function DebounceComponent() {
  const [input, setInput] = useState("10")
  const [users , setUsers] = useState([]);
  useDebounce(() => getUsers(), 1000, [input])


  async function getUsers() {
    const response = await fetch('/fake_data/users.json');
    const data = await response.json();

    setUsers(data.filter(el => el.email.includes(input)))
  }

  

  return (
    <div>
      <span>Search User</span>
      <input type={"text"} onChange={(e) => setInput(e.target.value)} />
      {users.map(el => <p>{el.email}</p>)}
      
    </div>
  )
}