import { useState } from "react"
import useDebounce from "../hooks/useDebounce"

export default function DebounceComponent() {
  const [input, setInput] = useState("10")
  useDebounce(() => callback(), 1000, [input])


  function callback() {
    fetch('/fake_data/users.json').then(response => response.json()).then(data => console.log(data))
  }

  

  return (
    <div>
      <span>Search User</span>
      <input type={"text"} onChange={(e) => setInput(e.target.value)} />
      
    </div>
  )
}