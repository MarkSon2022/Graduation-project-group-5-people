import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './Comments.css';
import { BASE_URL } from '../../data/const';

const Comments = ({ course }) => {
  const [comments, setComments] = useState([]);
  const [text, setText] = useState('');
  const [replyTexts, setReplyTexts] = useState([]);
  const [user, setUser] = useState(null);

  useEffect(() => {
    const userJSON = localStorage.getItem('USER');
    if (userJSON) {
      setUser(JSON.parse(userJSON));
    }

    // Parse comments from the course object
    if (course.comment) {
      setComments(JSON.parse(course.comment));
    }
  }, [course]);

  useEffect(() => {
    // Initialize replyTexts state with empty strings for each comment
    setReplyTexts(Array(comments.length).fill(''));
  }, [comments]);

  const getCurrentTime = () => {
    return new Date().toLocaleString();
  };

  const handleCommentSubmit = (e) => {
    e.preventDefault();
    const newComment = {
      text,
      replies: [],
      user: user ? { userId: user.userId, fullName: user.fullName } : null,
      createdTime: getCurrentTime()
    };
    const updatedComments = [...comments, newComment];
    setComments(updatedComments);
    setText('');

    // Update comments in the course object
    updateCommentsInCourse(updatedComments);
  };

  const handleReplySubmit = (commentIndex) => {
    const updatedComments = [...comments];
    updatedComments[commentIndex].replies.push({
      text: replyTexts[commentIndex], // Use the specific replyText based on commentIndex
      user: user ? { userId: user.userId, fullName: user.fullName } : null,
      createdTime: getCurrentTime()
    });
    setComments(updatedComments);

    // Reset the replyText for the current commentIndex
    setReplyTexts(prevState => {
      const updatedTexts = [...prevState];
      updatedTexts[commentIndex] = '';
      return updatedTexts;
    });

    // Update comments in the course object
    updateCommentsInCourse(updatedComments);
  };

  const updateCommentsInCourse = async (updatedComments) => {
    try {
      // Replace 'your_course_id' with the actual course ID
      const courseId = course.id;
      await axios.put(BASE_URL + 'api/v1/courses/comment', {
        id: courseId,
        comment: JSON.stringify(updatedComments)
      });
      console.log('Comments updated in the course object successfully!');
    } catch (error) {
      console.error('Error while updating comments in the course object:', error);
    }
  };

  return (
    <div className="comment-app">
      <h1>Put Your Comment</h1>
      <form onSubmit={handleCommentSubmit}>
        <textarea
          className='main-comment-input'
          value={text}
          onChange={(e) => setText(e.target.value)}
          placeholder="Write your comment here..."
        />
        <button className='main-comment-btn' type="submit">Post Comment</button>
      </form>
      <div className="comment-list">
        {comments.map((comment, index) => (
          <div className="comment" key={index}>
            <div className='comment-show'>
              <p>
                <span style={{ fontWeight: 'bold' }}>{comment.user ? comment.user.fullName : 'Anonymous'}: </span>
                {comment.text}</p>
              <p className='time-show'>Posted at: {comment.createdTime}</p>
            </div>
            <div className="replies">
              {comment.replies.map((reply, i) => (
                <div className="reply" key={i}>
                  <p>
                    <span style={{ fontWeight: 'bold' }}>{reply.user ? reply.user.fullName : 'Anonymous'}: </span>
                    {reply.text}</p>
                  <p className='time-show'>Posted at: {reply.createdTime}</p>
                </div>
              ))}
            </div>
            <input
              type="text"
              value={replyTexts[index]} // Use the specific replyText based on commentIndex
              onChange={(e) => setReplyTexts(prevState => {
                const updatedTexts = [...prevState];
                updatedTexts[index] = e.target.value;
                return updatedTexts;
              })}
              className='reply-input'
              placeholder="Write your reply here..."
            />
            <button className='reply-btn' onClick={() => handleReplySubmit(index)}>Post Reply</button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Comments;
