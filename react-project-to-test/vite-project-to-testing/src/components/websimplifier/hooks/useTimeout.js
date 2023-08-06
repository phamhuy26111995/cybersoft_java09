import { useCallback, useEffect, useRef } from "react"

export default function useTimeout(callback, delay) {
  const callbackRef = useRef(callback)
  const timeoutRef = useRef()

  useEffect(() => {
    console.log('callback effect')
    callbackRef.current = callback
  }, [callback])

  const set = useCallback(() => {
    console.log('1')
    timeoutRef.current = setTimeout(() => callbackRef.current(), delay)
  }, [delay])

  const clear = useCallback(() => {
    console.log('clear')
    timeoutRef.current && clearTimeout(timeoutRef.current)
  }, [])

  useEffect(() => {
    console.log('delay set clear effect')
    set()
    return clear
  }, [delay, set, clear])

  const reset = useCallback(() => {
    clear()
    set()
  }, [clear, set])

  return { reset, clear }
}