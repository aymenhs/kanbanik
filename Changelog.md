# Version 0.2.8 #
The main intent of this release was to enhance the usability of Kanbanik by various (mainly frontend) changes
  * Add filters on Boards screen ([Issue 8](https://code.google.com/p/kanbanik/issues/detail?id=8))
  * Notify user when the WIP limit is violated ([Issue 41](https://code.google.com/p/kanbanik/issues/detail?id=41))
  * Implement push notifications ([Issue 45](https://code.google.com/p/kanbanik/issues/detail?id=45))
  * Show the whole "short description" without the need to resize the task ([Issue 46](https://code.google.com/p/kanbanik/issues/detail?id=46))
  * Make the new/edit task dialog resizable ([Issue 47](https://code.google.com/p/kanbanik/issues/detail?id=47))
  * The whole boards screen has been redesigned so it will take less space (e.g. way more tasks and more complex flows fit into one screen)
  * Also contains lots of code cleanups and some performance enhancements

# Version 0.2.7 #
A very small fixing release which fixes one very nasty bug
  * Fixed "Task description does not allow & (ampersand) and =>" ([Issue 42](https://code.google.com/p/kanbanik/issues/detail?id=42))

# Version 0.2.6 #
The main intent of this release is to make the integration of Kanbanik with any external services (such as Bugzilla, Gerrit, mail etc) possible by implementing a simple yet complete JSON based API.
  * Implement API ([Issue 23](https://code.google.com/p/kanbanik/issues/detail?id=23))
  * Again massive performance enhancements both frontend and server side

# Version 0.2.5 #
The main intent of this release is to enhance the usability of Kanbanik
The most important features/enhancements:
  * Editable task type ([Issue 4](https://code.google.com/p/kanbanik/issues/detail?id=4))
  * Assign user to task ([Issue 30](https://code.google.com/p/kanbanik/issues/detail?id=30))
  * Due date for task ([Issue 25](https://code.google.com/p/kanbanik/issues/detail?id=25))
  * Enter / Escape binding on dialogs ([Issue 31](https://code.google.com/p/kanbanik/issues/detail?id=31))
  * Ability to set fixed amount of tasks per workflowitem ([Issue 32](https://code.google.com/p/kanbanik/issues/detail?id=32))
  * Short description as title element ([Issue 24](https://code.google.com/p/kanbanik/issues/detail?id=24))
  * Keep the order of the tasks ([Issue 33](https://code.google.com/p/kanbanik/issues/detail?id=33))
  * Multidelete of tasks (ctrl+click and delete button)

# Version 0.2.4-1 #
Fixing release. Contains only one issue fixed:
  * Task description can disappear ([Issue 28](https://code.google.com/p/kanbanik/issues/detail?id=28))

# Version 0.2.4 #
The main intent of this release is to bring authorization/authentication.
It contains the following main fixes/enhancements:
  * Added simple authentication ([Issue 3](https://code.google.com/p/kanbanik/issues/detail?id=3))
  * Added automatic database migration ([Issue 17](https://code.google.com/p/kanbanik/issues/detail?id=17))
  * Some of performance improvements
  * Made the tasks a bit smaller ([Issue 18](https://code.google.com/p/kanbanik/issues/detail?id=18))
  * Support for changing the workflowitems alignment ([Issue 19](https://code.google.com/p/kanbanik/issues/detail?id=19))

# Version 0.2.3 #
This release mainly refactors the backend to improve the performance by changing the data structure to reflect the real data.
It also the following bugs:
  * Chrome - edit task dialog not correctly aligned ([Issue 12](https://code.google.com/p/kanbanik/issues/detail?id=12))
  * Vertical stretch of workflowitems is not always correct ([Issue 13](https://code.google.com/p/kanbanik/issues/detail?id=13))
  * Performance issue - on load more than one server call is issued ([Issue 14](https://code.google.com/p/kanbanik/issues/detail?id=14))
  * Workflowitem drop target is not the whole workflowitem ([Issue 15](https://code.google.com/p/kanbanik/issues/detail?id=15))

# Version 0.2.2 #
The intent of this release is to make the life of the people using Kanbanik from touch screen devices more simple. It contains the following issues fixed:
  * The cancel button is too close to ok button in dialogs  ([Issue 10](https://code.google.com/p/kanbanik/issues/detail?id=10))
  * On tablets: when open any dialog it can be moved outside of the screen ([Issue 11](https://code.google.com/p/kanbanik/issues/detail?id=11))

# Version 0.2.1 #
This is a much smaller release than 0.2.0.
It was mostly dedicated to make the DB access more secure by detecting and avoiding mid-air collisions.

It also contains some small usability improvements:
  * Don't close popup when an error occurs on backend
  * Make the subtask's width 100% (noticeable when using more swimlines)

# Version 0.2.0 #
This release was mostly dedicated to:
  * Pay for technology depth
  * Bring support for complex flow
In practice it means that the whole backend has been rewritten from Java + Spring + JPA to Scala + MongoDB (casbah). The structuring of the backend has been rewritten from lots of services to the command pattern.

New features and bug fixes:
  * Fix: Editor buttons not viewable ([Issue 1](https://code.google.com/p/kanbanik/issues/detail?id=1))
  * Feature: Support for sub-states (horizontal and vertical)
  * Feature: Mobile devices (touch screen) now supported (thanks to new gwt-dnd library)
  * Feature: Instead of having a all-in-one package created separate runtimes for Linux and Windows